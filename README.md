# urKitchenAPI

# for new version of MySQL
use urKitchen;
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
