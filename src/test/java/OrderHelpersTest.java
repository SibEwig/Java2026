import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderHelpersTest {

    @Test
    public void formatBuildsHumanString() {
        String actualString = OrderHelpers.format(42, "PAID", 199.0);

        assertThat(actualString).isEqualTo("Order #42 | PAID | 199.0");
    }

    @Test
    void finalForPaidNotForCreated() {
        boolean actualWithPaid = OrderHelpers.isFinal("PAID");
        boolean actualWithCreated = OrderHelpers.isFinal("CREATED");

        assertThat(actualWithPaid).isEqualTo(true);
        assertThat(actualWithCreated).isEqualTo(false);
    }

    @ParameterizedTest(name = "{0} → {1}")
    @CsvSource({
            "CREATED, false",
            "PAID, true",
            "READY, true",
            "COMPLETED, true",
            "UNKNOWN, false"
    })
    void finalStatusTable(String status, boolean expected) {
        assertThat(
                OrderHelpers.isFinal(status)
        ).isEqualTo(expected);
    }

    @Test
    void keepFinalFiltersOnlyFinalStatuses() {
        List<String> source = List.of(
                "CREATED",
                "PAID",
                "READY",
                "CANCELLED"
        );
        List<String> result = OrderHelpers.keepFinal(source);

        assertThat(result).hasSize(3);
        assertThat(result).contains("PAID");
        assertThat(result).contains("READY");
        assertThat(result).doesNotContain("CREATED");
    }
}
