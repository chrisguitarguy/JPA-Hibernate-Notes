package org.chrisguitarguy.hibernateplay;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Persistence;

/**
 * An example of how Persistence.getSchema might be used to generate SQL.
 *
 * Good resource on what the properties are:
 * https://blogs.oracle.com/arungupta/entry/jpa_2_1_schema_generation
 */
class SchemaGenerator {
    private String outputDir;
    private String persistenceUnit;

    public SchemaGenerator(String outputDir, String unit) {
        this.outputDir = outputDir;
        this.persistenceUnit = unit;
    }

    public void generate() throws Exception {
        Persistence.generateSchema(
            persistenceUnit,
            getProperties(createOutputDirectory())
        );
    }

    /**
     * Get the properties we'll pass as the second arg to generateSchema.
     *
     * All of these CAN be set elsewhere (eg. in `persistence.xml`), but, for
     * the purposes of having notes. They're here too.
     */
    private Map<String, String> getProperties(File baseDir) {
        HashMap<String, String> props = new HashMap<String, String>();

        // tells generateSchema that we want both drop and create scripts
        props.put("javax.persistence.schema-generation.scripts.action", "drop-and-create");

        // specify where we want the create script to land
        props.put(
            "javax.persistence.schema-generation.scripts.create-target",
            (new File(baseDir, "create.sql").getPath())
        );

        // specify where we want the drop script to land
        props.put(
            "javax.persistence.schema-generation.scripts.drop-target",
            (new File(baseDir, "drop.sql").getPath())
        );

        return props;
    }

    private File createOutputDirectory() throws Exception {
        File baseDir = new File(outputDir);
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }

        if (!baseDir.isDirectory()) {
            throw new Exception(String.format("%s is not a directory", outputDir));
        }

        return baseDir;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println(String.format("Usage: {COMMAND} persistenceUnit [outputDirectory]"));
            return;
        }

        String unit = args[0];
        String outputDir = ".";
        if (args.length > 1) {
            outputDir = args[1];
        }

        System.out.println(unit);
        System.out.println(outputDir);

        SchemaGenerator sg = new SchemaGenerator(outputDir, unit);

        try {
            sg.generate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("Schema generated");

        // this is a bad idea, but this thing seems to keep running even
        // after the end of the method is reached. Maybe because of Hibernate?
        System.exit(0);
    }
}
