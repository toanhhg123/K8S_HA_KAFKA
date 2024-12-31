### run kafka zookeeper-dev

```bash
kubectl apply -f ./zookeeper-dev/namespace.yaml
kubectl apply -f ./zookeeper-dev
```


### run kafka kafka-dev

```bash
kubectl apply -f ./kafka-dev/namespace.yaml
kubectl apply -f ./kafka-dev
```


### forward port for kafka
```bash
kubectl port-forward svc/kafka-headless -n kafka 30093:9093
```


### delete all
```bash
kubectl delete -f ./kafka-dev
kubectl delete -f /zookeeper-dev
```

