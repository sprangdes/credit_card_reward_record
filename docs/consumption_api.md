# Consumption
`GET /api/v1/consumptions`
- 描述：取得所有消費通路
- 回應範例：
  List<Consumption>

`GET /api/v1/consumptions/{consumptionId}`
- 描述：以consumptionId取得消費通路
- 請求參數：
    - (Path) consumptionId：integer
- 回應範例：
  Consumption

`POST /api/v1/consumptions`
- 描述：有提供consumptionId時為更新；沒有提供consumptionId時為新增
- 請求參數：
```json
{
  "consumptionId": 0,
  "consumptionName": "string"
}
```
- 回應範例：
  Consumption

`DELETE /api/v1/consumptions/{consumptionId}`
- 描述：依consumptionId刪除一筆消費通路
- 請求參數：
    - (Path) consumptionId：integer
- 回應範例：
  `Consumption deleted successfully`