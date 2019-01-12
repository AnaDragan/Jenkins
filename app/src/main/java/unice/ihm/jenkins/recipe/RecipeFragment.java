package unice.ihm.jenkins.recipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import unice.ihm.jenkins.R;
import unice.ihm.jenkins.entities.Ingredient;
import unice.ihm.jenkins.entities.Recipe;

public class RecipeFragment extends Fragment {

    public static final String RECIPE_KEY = "recipe";

    private StepPagerAdapter pagerAdapter;
    private ViewPager pager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.follow_main, container, false);
        Recipe recipe = (Recipe) getArguments().get(RECIPE_KEY);
        pagerAdapter = new StepPagerAdapter(recipe, getActivity().getSupportFragmentManager());
        pager = root.findViewById(R.id.step_pager);
        pager.setAdapter(pagerAdapter);
        ArrayAdapter<Ingredient> arrayAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, recipe.getIngredients());
        ListView listView = root.findViewById(R.id.ingredientList);

        listView.setAdapter(arrayAdapter);

        FloatingActionButton next = root.findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.arrowScroll(View.FOCUS_RIGHT);
            }
        });
        FloatingActionButton previous = root.findViewById(R.id.previous_button);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.arrowScroll(View.FOCUS_LEFT);
            }
        });
        return root;
    }

}
