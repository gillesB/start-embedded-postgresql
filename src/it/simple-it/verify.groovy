File touchFile = new File( basedir, "target/touch.txt" );

assert touchFile.isFile()

File generatedAccountFile = new File( basedir, "target/generated-sources/jooq/lu/gbaatz/xml_generator/db_model/tables/Account.java");

assert touchFile.isFile()
