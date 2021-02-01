# Baza Postgresql

## Backup

### Realizacja
Backup realizowany jest za pomocą CronJob, który jest dostępny w Kubernetes. Sam pod bazy nie wykonuje backupu.
Zamiast tego, cron podnosi nową instancję postgresql (nowy pod o nazwie postgres-backup), łączy się z bazą po podanych
danych logowania i wykonuję dump bazy. Dump jest następnie zapisywany do zewnętrznego wolumenu danych w
`/var/volumes/postgres-backups`.

### Format backupu

Z racji, że backup jest dumpem bazy, backupy mają format plików sql. Format nazwy plików zawiera datę wykonania backupu:
`backup-<<miesiąc-dzień-rok-godzina-minuta>>.sql`.

### Dane logowania

Dane logowania są zapisane w secret pod nazwą `pgpass` oraz zakodowane w Base64. Po rozszyfrowaniu dane mają format
`hostname:port:database:username:password`.

### Czas wykonywania backupu
Backup realizowany jest w każdą sobotę o północy (`crontab 0 0 * * 6`).

### Skrypt backup

Backup jest wykonywany jako prosty dump do pliku.

```shell script
/bin/sh -c echo "$PGPASS" > /root/.pgpass && chmod 600 /root/.pgpass && pg_dump -U myapp -h postgres enrollment > /var/backups/backup-$(date +"%m-%d-%Y-%H-%M").sql
```

### Źródło

Praktycznie cały pomysł pochodzi z tej stronki:
https://cwienczek.com/2020/06/simple-backup-of-postgres-database-in-kubernetes/

