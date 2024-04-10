package org.kevin.backendcostos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BackendCostosApplicationTests {

    Calculator calculator = new Calculator();

    @Test
    void itShouldAddNumbers() {
        // given
        int n1 = 20;
        int n2 = 30;

        // when
        int result = calculator.add(n1, n2);

        // then
        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }

    static class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }

}
