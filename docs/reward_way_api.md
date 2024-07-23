# RewardWay
`GET /api/v1/reward-ways`
- 描述：取得所有回饋方式
- 回應範例：
  List<RewardWay>

`GET /api/v1/reward-ways/{rewardWayId}`
- 描述：以rewardWayId取得回饋方式
- 請求參數：
    - (Path) rewardWayId：integer
- 回應範例：
  RewardWay

`GET /api/v1/reward-ways/name/{rewardWayName}`
- 描述：以rewardWayName取得回饋方式
- 請求參數：
    - (Path) rewardWayName：string
- 回應範例：
  RewardWay

`GET /api/v1/reward-ways/card/{cardId}`
- 描述：以cardId取得回饋方式列表
- 請求參數：
    - (Path) cardId：integer
- 回應範例：
  List<RewardWay>

`POST /api/v1/reward-ways`
- 描述：有提供rewardWayId時為更新；沒有提供rewardWayId時為新增
- 請求參數：
```json
{
  "rewardWayId": 0,
  "rewardWayName": "string",
  "rewardLimit": 0,
  "rewardRate": 0
}
```
- 回應範例：
  RewardWay

`PUT /api/v1/reward-ways/{rewardWayId}/consumption`
- 描述：提供rewardWayId及consumptionIds以將多個消費通路新增至指定回饋方式
- 請求參數：
    - (Path) rewardWayId：integer
    - (Query) consumptionIds：array[integer]
- 回應範例：
  RewardWay

`DELETE /api/v1/reward-ways/{rewardWayId}`
- 描述：依rewardWayId刪除一筆回饋方式
- 請求參數：
    - (Path) rewardWayId：integer
- 回應範例：
  `Reward Way deleted successfully`

`DELETE /api/v1/reward-ways/{rewardWayId}/consumption/{consumptionId}`
- 描述：提供rewardWayId及consumptionId刪除一筆回饋方式中的消費通路
- 請求參數：
    - (Path) rewardWayId：integer
    - (Path) consumptionId：integer
- 回應範例：
  `Consumption in Reward Way deleted successfully`