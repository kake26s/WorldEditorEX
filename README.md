# WorldEditorEX
Jupiter/Nukkit用ワールドエディタ  
  
## ダウンロード
<a href="https://github.com/itsu020402/Plugins/blob/master/WorldEditorEX.jar">https://github.com/itsu020402/Plugins/blob/master/WorldEditorEX.jar</a>    

## 主な機能
- ブロック一括設置(/set [id:meta])  
- ブロック一括削除(/cut)  
- ブロック一括変更(/replace from[id:meta] to[id:meta])  
- コピー(/copy)  
- ペースト(/paste)  
- コピーされたオブジェクトの外部エクスポート(/export [Name])  
- エクスポートされたファイルのインポート(/import [Name]) 
- インポート可能ファイルリストの表示(/objects)  
- 手に持っているアイテムのid確認(/bid)  
- 座標確認(/xyz)  
  
## 使い方
- 範囲指定: 木の斧をもってブロックをタップ/破壊  
  
## エクスポート方式について
JSONでエクスポートされます。x/y/zそれぞれが基準点から何ブロック離れているかで記録しています。  
フォーマット: "x,y,z":"id:meta"  
  
