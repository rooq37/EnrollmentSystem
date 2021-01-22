# Budowanie aplikacji

## Obraz
Dopóki nie skonfigurujemy Jenkinsa, proces uruchamiania frontendu wygląda podobnie,
jak na ostatnich laboratoriach z Kubernetesa. Jak już będziemy mieli Jenkins załatawiony,
to będzie trzeba to zmienić. Oczywiście minikube musi być włączony itd.

### Tworzenie obrazu w minikube
```shell script
cd /
sudo mkdir app
cd app
sudo git clone https://github.com/rooq37/EnrollmentSystem
cd EnrollmentSystem/deployment/frontend
sudo docker build -t frontend:1.0 ../../frontend
```

### Tworzenie serwisu w Kubernetes
```shell script
# cd <<ścieżka do projektu>>/EnrollmentSystem/frontend
kubectl create -f frontend.yml

# klient nie kontaktuje się z backend po wewnętrznej sieci k8s,
# tylko przez http 
kubectl port-forward service/backend-service 8080:8080

# na razie w celach testowych
# będzie trzeba zmienić, jak już będzie jenkins, żeby samo odkrywało port 
kubectl port-forward service/frontend-service 4200:4200
```

---
