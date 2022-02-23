pipeline {
    agent any 
    
    stages { 
        stage('Clone repo and chenge config') { 
            steps { 
                git branch: "master", url: "https://github.com/Cmertho/sber_ansible.git"
                writeFile file: 'inventory/hosts', text: "jenkins ansible_host=$IP_JENKINS ansible_user=$USER_JENKINS\ndb ansible_host=$IP_DB ansible_user=$USER_DB"
                writeFile file: 'inventory/host_vars/db', text: "greenplum_admin_user: $GREENPLUM_USER\ngreenplum_admin_password: $GREENPLUM_PASSWORD\n"
            }
        }
        // stage('Deploy db'){
        //     steps{
        //         ansiblePlaybook inventory: 'inventory/', playbook: 'deploy-db-nginx.yml', tags: 'install_db'
        //     }    
        // }
        // stage('Test install nginx with running db'){
        //     steps{
        //         ansiblePlaybook inventory: 'inventory/', playbook: 'deploy-db-nginx.yml', tags: 'test_install_nginx'
        //     }  
        // }
        // stage('Test install nginx with not running db'){
        //     steps{
        //         ansiblePlaybook inventory: 'inventory/', playbook: 'deploy-db-nginx.yml', tags: 'test_not_install_nginx'
        //     }  
        // }
    }
}
