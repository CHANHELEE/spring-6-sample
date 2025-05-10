package tobyspring.hellospring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SortTest {

    Sort sort;


    @BeforeEach
    void beforeEach() {
        sort = new Sort();
    }

    @Test
    void sort() {

        // when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // then
        assertThat(list).isEqualTo(List.of("b", "aa"));

    }

    @Test
    void sort3Items() {

        // when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        // then
        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));

    }

    @Test
    void sortAlreadySorted() {
        Sort sort = new Sort();
        System.out.println(this);
        // when
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        // then
        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));

    }
}