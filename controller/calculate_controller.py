import json
from uuid import UUID

from flask import Blueprint, request

from util.caulculate_util import Point
from util.sqlConnect import (City,session)
from util.MessageUtil import *
from util.caulculate_util import do_caulculate

caulculate_api=Blueprint("caulculate_api",import_name=__name__)

@caulculate_api.route("/caulculate",methods=["POST"])
def caulculate():
    data=request.json
    city_id=data["id"]
    station_point=Point(data["point"]["x"],data["point"]["y"])
    target_city=session.query(City).filter(City.id==city_id).one()
    city_point=Point(target_city.x,target_city.y)
    result=do_caulculate(city_point,station_point,ring_limit=target_city.ringLimit,
                         area_distance=target_city.distanceDelta,
                         area_delta=target_city.chargingAreaDelta)
    newR=dict()
    newR["number"]=result.get_number()
    newR["distance"]=result.get_distance()
    return send_ok_message_only_data(newR)
