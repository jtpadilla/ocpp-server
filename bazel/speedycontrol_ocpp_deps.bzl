load("@rules_jvm_external//:defs.bzl", "maven_install")

def speedycontrol_ocpp_deps():
    maven_install(
        artifacts = [
            "com.google.inject:guice:5.0.1",
            "eu.chargetime.ocpp:v1_6:1.0.1",
            "eu.chargetime.ocpp:common:1.0",
            "javax.xml.bind:jaxb-api:2.1",
            "org.slf4j:slf4j-api:1.7.25",
            "javax.xml.soap:javax.xml.soap-api:1.4.0",
            "com.google.code.gson:gson:2.8.0",
            "org.java-websocket:Java-WebSocket:1.5.1",
        ],
        generate_compat_repositories = True,
        repositories = [
            "https://repo.maven.apache.org/maven2/",
            "https://mvnrepository.com/",
        ],
    )
