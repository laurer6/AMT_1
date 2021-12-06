FROM payara/server-full
COPY drivers/mysql-connector-java-8.0.26.jar $PAYARA_DIR/glassfish/lib/
COPY commands.asadmin $POSTBOOT_COMMANDS
COPY locationApp.war $DEPLOY_DIR
