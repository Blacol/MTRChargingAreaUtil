def send_ok_message(data: object,detail: str):
    return {
        "code": 200,
        "data": data,
        "detail": detail
    }
def send_ok_message_only_data(data: object):
    return {
        "code": 200,
        "data": data
    }
def send_fail_message(code,detail):
    return {
        "code":code,
        "detail":detail
    }