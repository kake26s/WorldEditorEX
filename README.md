# WorldEditorEX
Jupiter/Nukkit用ワールドエディタ  

## 親リポジトリ
<a href="https://github.com/itsu020402/WorldEditorEX">itsu020402/WorldEditorEX</a>
  
## ダウンロード
<a href="https://github.com/kake26s/WorldEditorEX/blob/master/build/WorldEditorEX.jar">https://github.com/kake26s/WorldEditorEX/blob/master/build/WorldEditorEX.jar</a>    

## 追加機能
- ブロック一括ランダム設置

## 主な機能
- ブロック一括ランダム設置(/set [id:meta,id:meta,...])  
- ブロック一括削除(/cut)  
- ブロック一括変更(/replace from[id:meta] to[id:meta])  
- コピー(/copy)  
- ペースト(/paste)  
- コピーされたオブジェクトの回転  
- コピーされたオブジェクトの外部エクスポート(/export [Name])  
- エクスポートされたファイルのインポート(/import [Name]) 
- インポート可能ファイルリストの表示(/objects)  
- 手に持っているアイテムのid確認(/bid)  
- 座標確認(/xyz)  
- ヘルプ(/wehelp)  
詳しくはゲーム内で/wehelpコマンドを実行して確認してください。  
  
## 使い方
- 範囲指定: 木の斧をもってブロックをタップ/破壊  
  
## エクスポート方式について
JSONでエクスポートされます。x/y/zそれぞれが基準点から何ブロック離れているかで記録しています。  
フォーマット: "x,y,z":"id:meta"  
