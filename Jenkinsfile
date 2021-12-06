pipeline{
  agent any
  tools{
    maven 'maven3'
  }
  stages {
    
      stage("test"){
      steps {
          echo 'Testing test for web hook application....'
          sh 'mvn test'
      }
    }
    
    
    stage("build"){
      steps {
         echo 'Building test application....'
      }
    }
    
    
    stage("deploy"){
      steps {
         echo 'Deploying application....'
      }
    }
    
  }

}
