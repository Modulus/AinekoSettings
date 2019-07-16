// this guarantees the node will use this template
def label = "mypod-${UUID.randomUUID().toString()}"
podTemplate(label: label, containers : [
    containerTemplate( name: "gradle", image: "gradle:4.10.0-jdk8", command: "cat", ttyEnabled: true),
    containerTemplate( name: "postgres", image: "postgres:10.5", ports: [portMapping(name: 'posgresql', containerPort: 5432, hostPort: 5432)]),
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
                ls -la
                cp `pwd` /home/gradle/project
                cd /home/gradle/project && gradle test 
                """
            }
        }
        stage("Run integration tests"){
            container("gralde"){
                sh """
                gradle veify
                """
            }
        }
        stage("Junit reports"){
            junit 'build/reports/tests/**/*report.xml'
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
