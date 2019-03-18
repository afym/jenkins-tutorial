# Jenkins tutorial


###### Settung up your nodes
```
cd infrastructure
vagrant up
```

###### Destroying your nodes
```
cd infrastructure
vagrant destroy
```

###### Shutdown your nodes
```
cd infrastructure
vagrant halt
```

###### Accesing your nodes
```
cd infrastructure
vagrant ssh jenkins-master
vagrant ssh jenkins-slave
```

## Jenkins ansible playbook

###### Reusing playbooks in local mode (create a hosts file)
```
cd infrastructure/provision/master
ansible-playbook -i hosts playbook/hostaname.yml --connection=local
```

###### hosts file
```
[local]
127.0.0.1
```