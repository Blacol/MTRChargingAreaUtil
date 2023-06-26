import datetime
import json

from sqlalchemy import (
    create_engine,
    Column,
    Integer,
    String,
    Enum,
    DECIMAL,
    DateTime,
    Boolean,
    UniqueConstraint,
    Index,
    Text
)
from sqlalchemy.orm import declarative_base, sessionmaker, scoped_session
engine = create_engine("sqlite:///systemDB", echo=True)
Base=declarative_base()

class City(Base):
    __tablename__ = "city"
    id = Column(String(20), primary_key=True)
    name=Column(Text)
    ringLimit = Column(Integer, nullable=False,default=10,name="ring_limit")
    chargingAreaDelta = Column(Integer,default=1,name="charging_area_delta")
    distanceDelta = Column(Integer,default=100,name="distance_delta")
    x=Column(Integer,default=0)
    y=Column(Integer,default=0)
    def to_json(self):
        dict = self.__dict__
        if "_sa_instance_state" in dict:
            del dict["_sa_instance_state"]
        return dict
# 创建表
Base.metadata.create_all(engine)

# 绑定引擎
Session = sessionmaker(bind=engine)
# 创建数据库链接池，直接使用session即可为当前线程拿出一个链接对象conn
# 内部会采用threading.local进行隔离
session = scoped_session(Session)


if __name__ == "__main__":
    # 创建表
    Base.metadata.create_all(engine)
