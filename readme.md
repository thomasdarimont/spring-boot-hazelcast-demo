# Kubernetes

```bash

minikube start

eval $(minikube docker-env)

kubectl apply -f src/main/fabric8/clusterRoleBinding.yaml

mvn clean package fabric8:build 

mvn fabric8:resource fabric8:deploy

mvn fabric8:start  -Dfabric8.replicas=3
```