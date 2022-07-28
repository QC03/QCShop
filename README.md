# QCShop


# **기획**

## **Command**

1. /상점 생성 <display_name>
2. /상점 목록
3. /상점 열기 <shop_id>
4. /상점 설정 <shop_id>
5. /상점 상세설정 <obj>
 

## **Database** *(MariaDB)*

#### 1. ShopList
#### 2. ShopId
#### 3. ShopPrice
  - Money
  - Cash
  - Items
#### 4. ShopNPC
#### 5. ShopGUI
  - General
  - Setting
  - PriceSet
  - ItemPriceSet



## **Events**

#### 1. NpcClick
#### 2. GUIClick
   - Setting
   - General
   - PriceSet
#### 3. GUIClose
   - ItemPrice
