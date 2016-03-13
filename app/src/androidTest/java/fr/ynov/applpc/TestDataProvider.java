package fr.ynov.applpc;

import android.test.AndroidTestCase;

import fr.ynov.applpc.data.DataParentsProvider;
import fr.ynov.applpc.data.DataStudentsProvider;

/**
 * Created by clément on 09/02/2016.
 */
public class TestDataProvider extends AndroidTestCase {
    public void testParentsProvider(){
        DataParentsProvider dataParentsProvider = new DataParentsProvider(getContext());
        assertNotNull(dataParentsProvider.getDatas());
    }

    public void testStudentsProvider(){
        DataStudentsProvider dataStudentsProvider = new DataStudentsProvider(getContext());
        assertNotNull(dataStudentsProvider.getClasses());
        assertNotNull(dataStudentsProvider.getScheduleByClass(dataStudentsProvider.getClasses()[0]));
    }
}
