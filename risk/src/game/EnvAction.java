package game;

import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceAction;

import java.util.Map;
import java.util.Set;

/**
 * Created by José on 10/12/2015.
 */
public class EnvAction implements ISpaceAction{

    @Override
    public Object perform(Map<String, Object> map, IEnvironmentSpace iEnvironmentSpace) {
        return null;
    }

    @Override
    public Object getProperty(String s) {
        return null;
    }

    @Override
    public Set getPropertyNames() {
        return null;
    }

    @Override
    public void setProperty(String s, Object o) {

    }

    @Override
    public boolean hasProperty(String s) {
        return false;
    }
}
