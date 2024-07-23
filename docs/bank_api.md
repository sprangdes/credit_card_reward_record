# Bank
`GET /api/v1/banks`
- 描述：取得所有發卡銀行資料
- 回應範例：
  List<Bank>

`GET /api/v1/banks/{bankId}`
- 描述：以bankId取得發卡銀行資料
- 請求參數：
    - (Path) bankId：integer
- 回應範例：
  Bank

`GET /api/v1/banks/name/{bankName}`
- 描述：以bankName取得發卡銀行資料
- 請求參數：
    - (Path) bankName：string
- 回應範例：
  Card

`POST /api/v1/banks`
- 描述：有提供bankId時為更新；沒有提供bankId時為新增
- 請求參數：
```json
{
  "bankId": 0,
  "bankName": "string"
}
```
- 回應範例：
  Bank

`DELETE /api/v1/banks/{bankId}`
- 描述：依bankId刪除一筆發卡銀行資料
- 請求參數：
    - (Path) bankId：integer
- 回應範例：
  `Bank deleted successfully`