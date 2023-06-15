@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.serialization)
}

dependencies {
    implementation(projects.metaAction)
    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutines.core)

    implementation(libs.bundles.openai)

    implementation(libs.onnxruntime)
    implementation(libs.huggingface.tokenizers)
    implementation(libs.jtokkit)

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.test.junit.engine)
}
