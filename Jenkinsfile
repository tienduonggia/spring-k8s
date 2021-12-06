pipeline{
  agent any
  tools{
    maven '3.8.1'
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
