workspace(name = "ocpp_server")

RULES_JVM_EXTERNAL_TAG = "4.2"

RULES_JVM_EXTERNAL_SHA = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca"

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_pkg",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_pkg/releases/download/0.9.0/rules_pkg-0.9.0.tar.gz",
        "https://github.com/bazelbuild/rules_pkg/releases/download/0.9.0/rules_pkg-0.9.0.tar.gz",
    ],
    sha256 = "335632735e625d408870ec3e361e192e99ef7462315caa887417f4d88c4c8fb8",
)

load("@rules_pkg//:deps.bzl", "rules_pkg_dependencies")
rules_pkg_dependencies()


load("//bazel:ocpp_server_deps.bzl", "ocpp_server_git_repo", "ocpp_server_deps")

ocpp_server_git_repo()

load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS")
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS")

ocpp_server_deps(IO_GRPC_GRPC_JAVA_ARTIFACTS, IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS)

load("@maven//:compat.bzl", "compat_repositories")

compat_repositories()

load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")

grpc_java_repositories()

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()


