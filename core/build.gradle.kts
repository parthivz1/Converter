plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.ganga.core"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":zip"))
    // implementation(project(":image"))
}

/* -------------------------
   💚 Maven Publish Setup
   -------------------------
   IMPORTANT:
   - Must be inside afterEvaluate for Android modules
   - groupId MUST follow JitPack format
   - artifactId = module name (core, convert-image, etc.)
   - version = GitHub tag version (1.0.0, 1.0.1 etc.)
 */
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {

                // Publish AAR
                from(components["release"])

                // REQUIRED for JitPack
                groupId = "com.github.ganga.converter"
                artifactId = "core"  // CHANGE PER MODULE
                version = "1.0.0"    // CHANGE ON EVERY RELEASE TAG

                // Add dependencies to published POM
                pom {
                    withXml {
                        val depsNode = asNode().appendNode("dependencies")

                        // -----------------------------
                        // ZIP module dependency (active)
                        // -----------------------------
                        val zipDep = depsNode.appendNode("dependency")
                        zipDep.appendNode("groupId", "com.github.ganga.converter")
                        zipDep.appendNode("artifactId", "zip")
                        zipDep.appendNode("version", "1.0.0")
                        zipDep.appendNode("scope", "implementation")

                        // ------------------------------------------------
                        // IMAGE module dependency (commented placeholder)
                        // ------------------------------------------------
                        // val imageDep = depsNode.appendNode("dependency")
                        // imageDep.appendNode("groupId", "com.github.ganga.converter")
                        // imageDep.appendNode("artifactId", "image")
                        // imageDep.appendNode("version", "1.0.0")
                        // imageDep.appendNode("scope", "implementation")
                    }
                }

            }
        }
    }
}