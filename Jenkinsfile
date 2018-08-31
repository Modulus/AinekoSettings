// this guarantees the node will use this template
def label = "mypod-${UUID.randomUUID().toString()}"
podTemplate(label: label, containers : [
    containerTemplate( name: "gradle", image: "gradle:4.10.0-jdk8", ttyEnabled: true),
    containerTemplate( name: "postgres", image: "postgres:10.5"),
    containerTemplate( name: "docker", image: "docker", command: "cat", ttyEnabled: true)
    ],
    volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]) {

    node(label) {
       def repo = checkout scm
       def gitCommit = repo.GIT_COMMIT
       def gitBranch = repo.GIT_BRANCH
       def versionNumber = gitCommit.substring(0,10)
        stage('Run tests') {
            container("gradle"){
                sh """
                gradle test 
                """
            }
        }
        stage("Junit reports"){
            junit 'tests/*report.xml'
         }

        stage("Build container"){
            container("docker"){
                docker.withRegistry("https://registry.hub.docker.com", "dockerhub"){
                    def builtImage = docker.build("rubblesnask/aineko-settings:1.${versionNumber}-${env.BRANCH_NAME}")
                    builtImage.push()
                }
            }
        }
    }
}
