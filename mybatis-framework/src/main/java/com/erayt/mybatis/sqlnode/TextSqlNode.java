package com.erayt.mybatis.sqlnode;

import com.erayt.mybatis.sqlnode.iface.SqlNode;
import com.erayt.mybatis.util.GenericTokenParser;
import com.erayt.mybatis.util.OgnlUtils;
import com.erayt.mybatis.util.SimpleTypeRegistry;
import com.erayt.mybatis.util.innerface.TokenHandler;

/**
 * @Description: 包含${}的动态sqlnode
 * @Author: Mr.Shen
 * @CreateDate: 2020/8/31 22:38
 * @Version: 1.0
 */
public class TextSqlNode implements SqlNode {

    private String sql;

    public TextSqlNode(String sql) {
        this.sql = sql;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {

        GenericTokenParser genericTokenParser = new GenericTokenParser("${", "}", new BindingTokenParser(dynamicContext));
        String parseSql = genericTokenParser.parse(this.sql);
        dynamicContext.appenSql(parseSql);
    }

    private static  class BindingTokenParser implements TokenHandler {

        private DynamicContext dynamicContext;
        public BindingTokenParser(DynamicContext dynamicContext) {
            this.dynamicContext = dynamicContext;
        }

         /*
          * @Description:   expression：比如说${username}，那么expression就是username username也就是Ognl表达式
          * @Author:         Mr.Shen
          * @CreateDate:     2020/9/2 22:51
          * @Param:          expression
          * @Version:        v1.0
          */
        @Override
        public String handleToken(String expression) {
            Object parmas = dynamicContext.getBindings().get("_parameter");
            if (parmas == null) {
                return "";
            }
            if (SimpleTypeRegistry.isSimpleType(parmas.getClass())) {
                return String.valueOf(parmas);
            }
            Object expObj = OgnlUtils.getValue(expression, parmas);
            return expObj == null ? "" : String.valueOf(expObj);
        }
    }
}