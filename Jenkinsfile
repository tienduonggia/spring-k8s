pipeline{
  agent any
  tools{
    maven 'maven3'
  }
  stages {
    
      stage("test"){
      steps {
          echo 'Testing application....'
          sh 'mvn test'
          echo '-----------------------TEST SUCCESSFUL-----------------------'
      }
    }
    
    
    stage("build"){
      steps {
         echo 'Building Jar file....'
         sh 'mvn clean intsall -DskipTests=true'
         echo 'Building docker images....'
         withDockerRegistry(credentialsId: '17e47e10-b157-4b87-8dfa-57779c14c9f9', url: 'https://index.docker.io/v1/') {
          sh 'docker build -t giatien310/springboot-postgres-k8s:1.0 .'
        }
        echo '-----------------------BUILD SUCCESSFUL-----------------------'
         
      }
    }

    stage("push to docker hub"){
      steps {
         echo 'Pushing images....'
         withDockerRegistry(credentialsId: '17e47e10-b157-4b87-8dfa-57779c14c9f9', url: 'https://index.docker.io/v1/') {
          sh 'docker push giatien310/springboot-postgres-k8s:1.0'
        }
        echo '-----------------------PUSH SUCCESSFUL-----------------------'
      }
    }
    
    
    stage("deploy"){
      steps {
         echo 'Deploying application....'
      }
    }
    
  }

}
