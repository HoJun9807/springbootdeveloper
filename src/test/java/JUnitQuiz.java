

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitQuiz {

    @Test
    public void junitQuiz() {
        String name1 = "John";
        String name2 = "John";
        String name3 = "Anna";


        assertThat(name1).isNotEmpty();
        assertThat(name2).isNotEmpty();
        assertThat(name3).isNotEmpty();

        assertThat(name1).isEqualTo(name2);
        assertThat(name1).isNotEqualTo(name3);
    }
}
