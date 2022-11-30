rootProject.name = "tenis-service"
include("test-integration")
include("src:test-integration")
findProject(":src:test-integration")?.name = "test-integration"
