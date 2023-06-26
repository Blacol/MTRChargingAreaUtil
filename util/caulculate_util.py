import math

x,y=0,0
class Point:
    def __init__(self,x,y):
        self.x=x
        self.y=y
    x=0.0
    y=0.0
class Result:
    __number=0
    __distance=0.0
    def get_number(self):
        return self.__number
    def get_distance(self):
        return self.__distance
    def set_number(self,number):
        self.__number=number
    def set_distance(self,distance):
        self.__distance=distance
def __distance(point1:Point,point2:Point):
    return math.sqrt(math.fabs(point2.x-point1.x)**2+math.fabs(point2.y-point1.y)**2)
def do_caulculate(center:Point,station:Point,area_distance,area_delta,ring_limit):
    dis=__distance(center,station)
    max_distance = ring_limit * area_distance
    ring=0
    if dis <= max_distance:
        ring=ring+math.ceil(dis/area_distance)*area_delta
    else:
        ring=ring_limit+math.ceil((dis-max_distance)/100)
    res=Result()
    res.set_number(ring)
    res.set_distance(dis)
    return res



