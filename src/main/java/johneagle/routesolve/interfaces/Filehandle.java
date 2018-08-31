package johneagle.routesolve.interfaces;

import johneagle.routesolve.library.DataList;

/**
 * Interface for reading files and configs.
 *
 * @author Johneagle
 */
public interface Filehandle {
    DataList<String> readAll(String fileName);

    void getConfigs(String configFileName);
}
