import json
import uuid
from uuid import UUID

from flask import Blueprint, request
from sqlalchemy.exc import SQLAlchemyError

from util.sqlConnect import (City, session)
from util.MessageUtil import *

city_api=Blueprint("city_api",url_prefix="/city",import_name=__name__)
@city_api.route("/create",methods=["POST"])
def create_city():
    new_city=request.json
    new_city["id"]=str(uuid.uuid1())[1:20]
    try:
        session.add(City(**new_city))
        session.flush()
        session.commit()
        return send_ok_message(new_city,"创建成功")
    except SQLAlchemyError:
        return send_fail_message(500, "创建失败，数据库插入失败")
@city_api.route("/getAll")
def get_all_city():
    try:
        list=[]
        cities = session.query(City).all()
        for c in cities:
            list.append(c.to_json())
        return send_ok_message_only_data(list)
    except SQLAlchemyError:
        return send_fail_message(500, "查询失败，数据库查询失败")
@city_api.route("/delete/<id>")
def delete_city(id):
    try:
        code = session.query(City).filter(City.id==id).delete()
        session.commit()
        return send_ok_message_only_data(code)
    except SQLAlchemyError:
        return send_fail_message(500, "删除失败，数据库删除失败")
@city_api.route("/update/<id>")
def update_city(id):
    try:
        new_city=request.json
        code = session.query(City).filter(City.id==id).update(new_city)
        session.commit()
        return send_ok_message_only_data(code)
    except SQLAlchemyError:
        return send_fail_message(500, "更新失败，数据库更新失败")
