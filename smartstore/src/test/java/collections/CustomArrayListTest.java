package collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.collections.CustomArrayList;

import static org.assertj.core.api.Assertions.*;

class CustomArrayListTest {

    private CustomArrayList<Integer> customArrayList;

    @BeforeEach
    void init() {
        customArrayList = new CustomArrayList<>(15);
    }

    @Test
    @DisplayName("add 메서드에 대한 테스트입니다.")
    void addTest() {
        customArrayList.add(3);

        System.out.println(customArrayList.toString());
        assertThat(customArrayList.get(0)).isEqualTo(3);
    }

    @Test
    @DisplayName("get 메서드 예외처리 테스트입니다.")
    void getIndexOutOfBoundsExceptionTest() {
        System.out.println(customArrayList.toString());
        assertThatThrownBy(() -> customArrayList.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("insert 기능 테스트입니다.")
    void insertTest() {
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.add(3);

        customArrayList.add(4, 1);
        customArrayList.add(100, 0);
        assertThat(customArrayList.get(0)).isEqualTo(100);
    }

}