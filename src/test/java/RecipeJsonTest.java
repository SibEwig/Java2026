import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeJsonTest {

    @Test
    void serializeViaBuilder() throws Exception {
        Recipe recipe = Recipe.builder()
                .id(1)
                .name("Margarita")
                .price(299.0)
                .build();
        String json = new ObjectMapper().writeValueAsString(recipe);

        assertThat(json)
                .contains("\"id\":1")
                .contains("\"name\":\"Margarita\"")
                .contains("\"price\":299.0");
    }

    @Test
    void deserializeOneRecipe() throws Exception {
        String json = """
                {
                  "id": 1,
                  "name": "Margarita",
                  "price": 299.0
                }
                """;
        Recipe recipe = new ObjectMapper().readValue(json, Recipe.class);

        assertThat(recipe.getId()).isEqualTo(1);
        assertThat(recipe.getName()).isEqualTo("Margarita");
        assertThat(recipe.getPrice()).isEqualTo(299.0);
    }

    @Test
    void deserializeListViaTypeReference() throws Exception {
        String json = """
                [
                  {
                    "id": 1,
                    "name": "Margarita",
                    "price": 299.0
                  },
                  {
                    "id": 2,
                    "name": "Pepperoni",
                    "price": 349.0
                  },
                  {
                    "id": 3,
                    "name": "Four Cheese",
                    "price": 399.0
                  }
                ]
                """;
        List<Recipe> recipes = new ObjectMapper().readValue(json, new TypeReference<>() {});

        assertThat(recipes).hasSize(3);
        assertThat(recipes)
                .extracting(Recipe::getName)
                .containsExactly(
                        "Margarita",
                        "Pepperoni",
                        "Four Cheese"
                );
    }
}