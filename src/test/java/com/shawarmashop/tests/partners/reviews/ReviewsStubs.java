package com.shawarmashop.tests.partners.reviews;

import com.shawarmashop.tests.partners.reviews.dto.GraphQLRatingResponse;
import com.shawarmashop.tests.partners.reviews.dto.RecipeRatingDto;
import com.shawarmashop.tests.partners.wiremock.StubBuilder;
import com.shawarmashop.tests.partners.wiremock.WiremockAdminClient;
import com.shawarmashop.tests.partners.wiremock.WiremockStubBase;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.shawarmashop.tests.partners.wiremock.StubBuilder.post;

public class ReviewsStubs extends WiremockStubBase {

    private static final String GRAPHQL_PATH = "/graphql";
    private static final String OPERATION_RECIPE_RATING = "RecipeRating";
    private static final String OPERATION_REVIEWS_BY_RECIPE = "ReviewsByRecipe";

    public ReviewsStubs(WiremockAdminClient admin) {
        super(admin);
    }

    public RatingBuilder ratingByRecipe(int recipeId) {
        return new RatingBuilder(recipeId);
    }

    public void reviewsByRecipe(long recipeId, String message) {
        admin.addMapping(post(GRAPHQL_PATH)
                .withPriority(1)
                .withJsonPath("$.operationName", OPERATION_REVIEWS_BY_RECIPE)
                .withJsonPath("$.variables.recipeId", String.valueOf(recipeId))
                .willReturnJson(200, GraphQLRatingResponse.error(message))
        );
    }

    @RequiredArgsConstructor
    public final class RatingBuilder {
        private final int recipeId;


        public void respondWith(RecipeRatingDto ratingDto) {
            admin.addMapping(graphqlMapping().willReturnJson(200, GraphQLRatingResponse.of(ratingDto)));
        }

        public void respondWithNull() {
            admin.addMapping(graphqlMapping().willReturnJson(200, GraphQLRatingResponse.of(null)));
        }

        public void respondWithGraphQLError(String message) {
            admin.addMapping(graphqlMapping().willReturnJson(200, GraphQLRatingResponse.error(message)));
        }

        private StubBuilder graphqlMapping() {
            return post(GRAPHQL_PATH)
                    .withPriority(1)
                    .withJsonPath("$.operationName", OPERATION_RECIPE_RATING)
                    .withJsonPath("$.variables.recipeId", String.valueOf(recipeId));
        }
    }
}
