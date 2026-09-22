package com.shawarmashop.tests.db.factories;

import com.shawarmashop.tests.db.TestDatabase;
import com.shawarmashop.tests.db.TestDbScope;
import com.shawarmashop.tests.db.repository.RecipeRepository;
import com.shawarmashop.tests.db.rows.RecipeRow;

import java.sql.Timestamp;
import java.time.Instant;

public class Inserts {

    public static RecipeRow recipe(RecipeFixture fixture) {
        Timestamp now = Timestamp.from(Instant.now());
        long id = TestDatabase.jdbc().insertReturningId(
                """
                           INSERT INTO public.recipes
                               (name, description, size, price, prep_seconds,
                               image_url, created_at, updated_at, created_by,
                               updated_by)
                               VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                        """,
                fixture.getName(), fixture.getDescription(), fixture.getSize(), fixture.getPrice(),
                fixture.getPrepSeconds(), fixture.getImageUrl(), now, now, "owner", "owner"
        );
        TestDbScope.current().track("public.recipes", id);
        return new RecipeRepository().findById(id).orElseThrow();
    }
}
