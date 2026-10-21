import main.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(1991);
        expectedYears.add(1992);
        expectedYears.add(1994);
        expectedYears.add(1995);

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>();
        expectedTotal.add(0.0);
        expectedTotal.add(100.0);
        expectedTotal.add(600.0);
        expectedTotal.add(500.0);

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    public void yearsTest() {
        TimeSeries ts = new TimeSeries();
        List<Integer> tsYears = new ArrayList<>();

        ts.put(2003, 0.1);
        tsYears.add(2003);
        assertThat(ts.years()).isEqualTo(tsYears);

        ts.put(1990, 3.6);
        tsYears.addFirst(1990);
        assertThat(ts.years()).isEqualTo(tsYears);
    }

    @Test
    public void dataTest() {
        TimeSeries ts = new TimeSeries();
        List<Double> tsYears = new ArrayList<>();

        ts.put(2003, 0.1);
        tsYears.add(0.1);
        assertThat(ts.data()).isEqualTo(tsYears);

        ts.put(1990, 3.6);
        tsYears.addFirst(3.6);
        assertThat(ts.data()).isEqualTo(tsYears);
    }

    @Test
    public void plusTest() {
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();

        TimeSeries ts3= ts1.plus(ts2);
        assertThat(ts3 != ts2).isTrue();

        ts1.put(1990, 4.5); ts1.put(2000, 3.6);
        ts2.put(1990, 2.5); ts2.put(1995, 2.4);
        ts3 = ts1.plus(ts2);
        List<Integer> years = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        years.add(1990); years.add(1995); years.add(2000);
        data.add(7.0); data.add(2.4); data.add(3.6);
        assertThat(ts3.years()).isEqualTo(years);
        assertThat(ts3.data()).isEqualTo(data);
    }

    @Test
    public void dividedByTest() {
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();

        ts1.put(1990, 1.0); ts1.put(1995, 2.0); ts1.put(2000, 4.0);
        ts2.put(1990, 2.0); ts2.put(1995, 4.0);

        ts2.dividedBy(ts1);
        assertThat(ts2.get(1990)).isEqualTo(2.0);
    }
}
