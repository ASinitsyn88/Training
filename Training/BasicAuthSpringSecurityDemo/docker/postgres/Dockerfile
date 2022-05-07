# Pull latest postgres image
FROM postgres:latest
# Set username
ENV POSTGRES_USER: postgres
# Set password
ENV POSTGRES_PASSWORD postgres
# Set database name
ENV POSTGRES_DB testdb
# Expose postgresql port to host machine
EXPOSE 5432/tcp
# Execute sql script. All scripts located in this folder will be automatically ran during container startup
COPY init.sql /docker-entrypoint-initdb.d/