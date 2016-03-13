package fr.ynov.applpc;

import android.test.AndroidTestCase;

import fr.ynov.applpc.data.DataCDIProvider;
import fr.ynov.applpc.data.DataCVLProvider;
import fr.ynov.applpc.data.DataHighSchoolProvider;
import fr.ynov.applpc.data.DataParentsProvider;
import fr.ynov.applpc.data.DataStudentsProvider;

/**
 * Created by clément on 09/02/2016.
 */
public class TestDataProvider extends AndroidTestCase {
    public void testParentsProvider(){
        DataParentsProvider dataParentsProvider = new DataParentsProvider(getContext());
        assertNotNull(dataParentsProvider.getDatas());
        assertNotNull(dataParentsProvider.getLastDate());
    }

    public void testStudentsProvider(){
        DataStudentsProvider dataStudentsProvider = new DataStudentsProvider(getContext());
        assertNotNull(dataStudentsProvider.getClasses());
        assertNotNull(dataStudentsProvider.getScheduleByClass(dataStudentsProvider.getClasses()[0]));
    }

    public void testHighSchoolProvider(){
        DataHighSchoolProvider dataHighSchoolProvider = new DataHighSchoolProvider(getContext());
        assertNotNull(dataHighSchoolProvider.getDatas());
        assertNotNull(dataHighSchoolProvider.getLastDate());
    }

    public void testCVLProvider(){
        DataCVLProvider dataCVLProvider = new DataCVLProvider(getContext());
        assertNotNull(dataCVLProvider.getDatas());
        assertNotNull(dataCVLProvider.getLastDate());
    }

    public void testCDIProvider(){
        DataCDIProvider dataCDIProvider = new DataCDIProvider(getContext());
        assertNotNull(dataCDIProvider.getDatas());
        assertNotNull(dataCDIProvider.getLastDate());
    }
}
