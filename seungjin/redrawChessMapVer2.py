# f = open("input.txt", 'r')
# line = f.readline()

line = input()
inputXY=line.split(" ")
ylen = int(inputXY[0])
xlen = int(inputXY[1])

chessMap = [[0]*xlen for i in range(ylen)]
chessMap1 = [[0]*xlen for i in range(ylen)]
# chessMap2 = [[0]*xlen for i in range(ylen)]



for i in range (ylen):
    # line = f.readline()
    line = input()
    for j in range (xlen):
        chessMap[i][j]=line[j]

# f.close()

min = 32

for y in range (ylen):
    for x in range (xlen):
        nextOne="B"
        if((y+x)%2==0):
            nextOne="B"
        else:
            nextOne="W"
            
        if(chessMap[y][x]!=nextOne):
            chessMap1[y][x]=1
        else:
            chessMap1[y][x]=0


for y in range (ylen-8+1):
    for x in range (xlen-8+1):
        #처음 black
        count =0
        for i in range (8):
            for j in range (8):
                count = count + chessMap1[y+i][x+j]
                
        if(count > 64-count):
            count = 64-count
        if(min > count ):
            min = count
        
        # #처음 white
        # count =0
        # for i in range (8):
        #     for j in range (8):
        #         count = count + chessMap2[y+i][x+j]
        
        # if(min > count ):
        #     min = count


print(min)





