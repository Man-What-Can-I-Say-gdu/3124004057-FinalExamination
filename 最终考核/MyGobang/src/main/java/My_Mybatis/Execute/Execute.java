package My_Mybatis.Execute;

import My_Mybatis.configration.Configuration;
import My_Mybatis.configration.MappedStatement;

import java.util.List;

/**
 * SQL执行器
 */
public interface Execute {
    /**
     * SQL执行器
     * @param configuration
     * @param mappedStatement
     * @param parms
     * @param <E>
     * @return
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object...parms)throws Exception;
}
