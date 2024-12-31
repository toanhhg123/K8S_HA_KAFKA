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



### delete all
```bash
kubectl delete -f ./kafka-dev
kubectl delete -f /zookeeper-dev
```