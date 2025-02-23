import com.pswidersk.gradle.python.VenvTask

plugins {
    id("com.pswidersk.python-plugin") version "2.8.2"
}

pythonPlugin {
    pythonVersion = "3.12.8"
}

tasks {

    register<Exec>("buildImage") {
        workingDir = projectDir.parentFile
        commandLine("docker", "build", "-t", "janus-local", "-f", "gradle-setup/Dockerfile", ".")
    }

    register<Exec>("runDemoContainer") {
        commandLine(
            "docker", "run",
            "-v", "janus-cache:/root/.cache",
            "-v", "gradle-cache:/root/.gradle",
            "-v", "gradle-app-cache:/app/gradle-setup/.gradle/",
            "-p", "8000:8000",
            "--name", "janus-container",
            "--rm",
            "-i", "janus-local",
            "runDemoScript"
        )
        standardInput = System.`in`
        dependsOn("buildImage")
    }

    val pipInstall by registering(VenvTask::class) {
        venvExec = "pip"
        workingDir = projectDir.parentFile
        args = listOf("install", "--isolated", "-e", ".")
    }

    register<VenvTask>("runDemoScript") {
        workingDir = projectDir.parentFile
        args = listOf("demo/fastapi_app.py")
        dependsOn(pipInstall)
    }

}

repositories {
    mavenLocal()
    gradlePluginPortal()
}

