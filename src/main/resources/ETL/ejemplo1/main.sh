#!/bin/bash
#export PGPASSWORD=$3;

#Directorio de Archivos Pentaho
SOURCE="${BASH_SOURCE[0]}"
DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
PENTAHO_JOB_CARGA_MIMUNDO=`echo $DIR'/Main.kjb'`

LOG_PATH=$DIR"/Log/"
LOG_NAME="csj_poi_$(date +%Y_%m_%d)"
LOG_FILE=`echo $LOG_PATH$LOG_NAME'.log'`

#Datos de la conexion de bd origen
IP_BD_ORIGEN="localhost"
PUERTO_BD_ORIGEN="5432"
NOMBRE_BD_ORIGEN="mimundo"
USUARIO_BD_ORIGEN="federix"
PASS_BD_ORIGEN="konecta"
ESQUEMA_ORIGEN="crm"

#Datos de la conexion de bd destino
IP_BD_DESTINO="localhost"
PUERTO_BD_DESTINO="1433"
NOMBRE_BD_DESTINO="csj_poi"
USUARIO_BD_DESTINO="sa"
PASS_BD_DESTINO="Konecta_123"
ESQUEMA_DESTINO="dbo"



#Vamos al directorio del data integration
export KETTLE_HOME="/home/konecta/instaladores/data-integration/"
cd $KETTLE_HOME

if [ -f "$PENTAHO_JOB_CARGA_MIMUNDO" ]; then
		
	echo Iniciando ETL...
	sh kitchen.sh -file $PENTAHO_JOB_CARGA_MIMUNDO \
			-param:host_origen=$IP_BD_ORIGEN \
			-param:port_origen=$PUERTO_BD_ORIGEN \
			-param:bbdd_origen=$NOMBRE_BD_ORIGEN \
            -param:user_origen=$USUARIO_BD_ORIGEN \
            -param:password_origen=$PASS_BD_ORIGEN \
            -param:schema_origen=$ESQUEMA_ORIGEN \
			-param:host_destino=$IP_BD_DESTINO \
			-param:port_destino=$PUERTO_BD_DESTINO \
			-param:bbdd_destino=$NOMBRE_BD_DESTINO \
            -param:user_destino=$USUARIO_BD_DESTINO \
            -param:password_destino=$PASS_BD_DESTINO \
            -param:schema_destino=$ESQUEMA_DESTINO \
			level=Debug >> $LOG_FILE
			
	echo ETL finalizado...

else
	echo No se encontro el archivo del ETL a ejectutar
fi