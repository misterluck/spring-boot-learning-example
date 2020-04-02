package org.example.sbgen.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * mybatis plus mysql 代码生成器
 * </p>
 *
 */
public class MysqlGenerator {

    private static final String url = "jdbc:mysql://10.21.171.61:3306/antdvue?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String username = "puaiuc";
    private static final String password = "Ailk@2019";
    private static final String tableName = "auth_info_admin";

    private static final String packagePath = "/org/example/sbgen";
    private static final String codePath = "/src/main/java";
    private static final String confPath = "/src/main/resources";

    private static final String author = "zhaolei";
    private static final String packageName = "org.example.sbgen";


    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // String projectPath = System.getProperty("user.dir");
        String projectPath = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").replace("target/classes/", "").trim();
        gc.setOutputDir(projectPath + codePath);
        gc.setAuthor(author);
        gc.setOpen(true);
        //service 命名方式
        gc.setServiceName("I%sService");
        //service impl 命名方式
        gc.setServiceImplName("I%sServiceImpl");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("I%sMapper");
        gc.setXmlName("I%sMapper");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(packageName);
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        // pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        //TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
        //TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        //tableFillList.add(createField);
        //tableFillList.add(modifiedField);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();

        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + confPath +"/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        focList.add(new FileOutConfig("/generator/vue/list.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + codePath
                        + packagePath + "/vue/" + tableInfo.getEntityName() + "List" + ".vue";
            }
        });
        focList.add(new FileOutConfig("/generator/vue/modules/modal.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + codePath
                        + packagePath + "/vue/modules/" + tableInfo.getEntityName() + "Modal" + ".vue";
            }
        });
        focList.add(new FileOutConfig("/generator/vue/modules/modal__Style#Drawer.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + codePath
                        + packagePath + "/vue/modules/" + tableInfo.getEntityName() + "Modal__Style#Drawer" + ".vue";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setXml(null);
        templateConfig.setController("/generator/controller.java");

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 设置逻辑删除键
        strategy.setLogicDeleteFieldName("deleted");
        strategy.setInclude(tableName);
        //strategy.setSuperEntityColumns("id");
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
