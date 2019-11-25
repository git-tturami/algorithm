# f = open("input.txt", 'r')
# line = f.readline()

line = input()
inputXY=line.split(" ")
ylen = int(inputXY[0])
xlen = int(inputXY[1])

chessMap = [[0]*xlen for i in range(ylen)]

for i in range (ylen):
    line = input()
    for j in range (xlen):
        chessMap[i][j]=line[j]

# f.close()

min = xlen * ylen 

for y in range (ylen-8+1):
    for x in range (xlen-8+1):
        #처음 black
        count =0
        for i in range (8):
            for j in range (8):
                nextOne="B"
                if((i+j)%2==0):
                    nextOne="B"
                else:
                    nextOne="W"
                if(chessMap[y+i][x+j]!=nextOne):
                    count = count + 1
                    
        
        if(min > count ):
            min = count
        
        #처음 white
        count =0
        for i in range (8):
            for j in range (8):
                nextOne="W"
                if((i+j)%2==0):
                    nextOne="W"
                else:
                    nextOne="B"
                if(chessMap[y+i][x+j]!=nextOne):
                    count = count + 1
        
        if(min > count ):
            min = count


print(min)





