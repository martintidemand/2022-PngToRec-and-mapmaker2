


local toolbar = plugin:CreateToolbar("Mapping v2")


local newScriptButton = toolbar:CreateButton("name", "make", "rbxassetid://0")

newScriptButton.ClickableWhenViewportHidden = true






function make(i,v,he,bi,pos,top)
	local new = nil
	local biome = Instance.new("Model",top)
	for j,k in ipairs(v) do
		local h = 1
		while h < #k do
			print(k[h],k[h+1],k[h+2],k[h+3])	
			new = Instance.new("Part",biome)
			new.Name = (he[i] .. "_" .. bi[i][1].."_"..bi[i][2].."_"..bi[i][3])
			new.Anchored = true
			new.Color = Color3.new(bi[i][1]/255,bi[i][2]/255,bi[i][3]/255)
			
			new.Size = Vector3.new(k[h+3]-k[h+1], he[i] ,k[h+2]-k[h])
			new.CFrame = CFrame.new((k[h+3]+k[h+1])/-2, pos+(he[i]/2) ,(k[h+2]+k[h])/2)
			new.Locked = true
			h = h + 4
			wait(0.1)
		end
	end
end


local function onNewScriptButtonClicked()
	local co = {{{1178, 28, 1179, 29}},
	{{100, 70, 101, 71}},
	{{416, 456, 423, 457}},
	{{886, 951, 887, 952}},
	{{576, 636, 580, 637}},
	{{315, 0, 337, 1}},
	{{537, 0, 550, 1, 537, 1, 549, 2, 537, 2, 548, 3}},
	{{157, 0, 174, 1}},
	{{584, 299, 585, 300}},
	{{1076, 896, 1079, 897}},
	{{1016, 80, 1018, 81}},
	{{360, 441, 362, 442}},
	{{391, 0, 407, 2}},
	{{519, 0, 537, 1}},
	{{1351, 57, 1352, 58}},
	{{612, 562, 614, 563}},
	{{834, 18, 837, 19}},
	{{23, 0, 100, 1, 23, 1, 99, 2}},
	{{48, 213, 49, 214, 48, 214, 50, 215}},
	{{407, 0, 408, 2}},
	{{1085, 89, 1086, 90}},
	{{41, 175, 42, 176}},
	{{279, 0, 288, 1}},
	{{1060, 650, 1061, 651}},
	{{500, 0, 505, 1}},
	{{555, 0, 567, 1, 555, 1, 568, 2}},
	{{1013, 84, 1015, 86}},
	{{1192, 27, 1194, 28}},
	{{116, 41, 120, 42}},
	{{408, 0, 419, 2}},
	{{49, 211, 50, 212, 49, 212, 51, 213, 49, 213, 52, 214}},
	{{44, 222, 45, 223}},
	{{215, 401, 221, 402}},
	{{1078, 643, 1083, 644}},
	{{1077, 643, 1078, 644}},
	{{771, 892, 772, 893}},
	{{1213, 60, 1216, 61}},
	{{1024, 62, 1025, 63}},
	{{1193, 0, 1209, 1}},
	{{3, 154, 4, 155}},
	{{534, 398, 538, 399}},
	{{340, 404, 343, 405}},
	{{681, 0, 682, 1}},
	{{496, 33, 497, 34}},
	{{288, 0, 304, 1}},
	{{141, 15, 149, 16}},
	{{1014, 80, 1016, 81}},
	{{1065, 647, 1069, 648}},
	{{0, 97, 1, 98, 0, 98, 2, 99, 0, 99, 4, 100, 0, 100, 5, 102, 0, 102, 6, 104, 0, 104, 4, 105, 0, 105, 2, 106}},
	{{419, 0, 420, 1, 419, 1, 447, 2}},
	{{872, 966, 873, 967, 872, 967, 874, 968}},
	{{1221, 38, 1223, 40}},
	{{133, 24, 135, 25}},
	{{578, 652, 579, 653}},
	{{100, 0, 116, 1}},
	{{335, 402, 337, 403}},
	{{304, 0, 315, 1, 304, 1, 314, 2}},
	{{89, 139, 90, 141}},
	{{358, 451, 364, 452}},
	{{447, 0, 467, 1, 447, 1, 469, 2}},
	{{1209, 0, 1314, 1, 1209, 1, 1319, 2, 1209, 2, 1354, 3, 1209, 3, 1355, 4}},
	{{45, 219, 46, 220}},
	{{199, 3, 202, 4, 199, 4, 204, 5}},
	{{1151, 10, 1156, 11}},
	{{331, 144, 332, 146}},
	{{4, 104, 7, 105}},
	{{1080, 897, 1081, 898}},
	{{0, 0, 23, 2, 0, 2, 21, 3, 0, 3, 20, 4, 0, 4, 19, 5, 0, 5, 17, 6, 0, 6, 14, 7, 0, 7, 11, 8, 0, 8, 8, 9, 0, 9, 5, 10, 0, 10, 3, 11, 0, 11, 2, 12}},
	{{116, 0, 157, 1}},
	{{1018, 81, 1020, 82}},
	{{614, 562, 619, 563}},
	{{1086, 0, 1193, 1, 1086, 1, 1188, 2, 1086, 2, 1185, 3}}}
	local he = {2.2, 5.8, 0.6, 1.4, 1.4, 3.0, 3.0, 1.0, 9.8, 0.6, 5.8, 1.4, 3.8, 3.8, 1.0, 0.6, 11.8, 1.0, 1.4, 3.8, 3.0, 2.2, 1.0, 1.4, 5.8, 5.8, 7.8, 1.4, 3.8, 5.8, 2.2, 0.6, 3.0, 0.4, 0.6, 2.2, 0.6, 3.8, 2.2, 3.0, 0.6, 3.8, 7.8, 7.8, 1.4, 2.2, 5.8, 1.0, 3.8, 7.8, 2.2, 1.0, 3.0, 2.2, 1.4, 5.8, 2.2, 7.8, 1.0, 9.8, 1.4, 1.0, 0.6, 3.0, 9.8, 5.8, 1.0, 0.6, 1.4, 3.8, 1.0, 2.2}
	local bi = {{91,154,76}, {164,189,71}, {193,190,66}, {58,125,21}, {52,142,64}, {255,204,153}, {215,197,154}, {164,189,71}, {105, 102, 92}, {58,125,21}, {40,127,71}, {193,190,66}, {255,204,153}, {215,197,154}, {40,127,71}, {52,142,64}, {242, 243, 243}, {148,190,129}, {75,151,75}, {124,156,107}, {91,154,76}, {193,190,66}, {255,204,153}, {31,128,29}, {255,204,153}, {215,197,154}, {91,154,76}, {91,154,76}, {164,189,71}, {124,156,107}, {75,151,75}, {75,151,75}, {75,151,75}, {44,101,29}, {31,128,29}, {31,128,29}, {91,154,76}, {40,127,71}, {40,127,71}, {193,190,66}, {255,204,153}, {75,151,75}, {255,204,153}, {215,197,154}, {255,204,153}, {164,189,71}, {91,154,76}, {31,128,29}, {193,190,66}, {124,156,107}, {58,125,21}, {91,154,76}, {164,189,71}, {52,142,64}, {148,190,129}, {75,151,75}, {255,204,153}, {164,189,71}, {193,190,66}, {105, 102, 92}, {40,127,71}, {75,151,75}, {164,189,71}, {40,127,71}, {105, 102, 92}, {193,190,66}, {58,125,21}, {148,190,129}, {164,189,71}, {91,154,76}, {52,142,64}, {148,190,129}}

	local pos = 2

	local top = Instance.new("Model",workspace)
	top.Name = "Map"

	water = Instance.new("Part",top)
	water.Name = "watersolid"
	water.Anchored = true
	water.Color = Color3.new(13/255,105/255,172/255)
	water.Size = Vector3.new(1084,1,1380)
	water.CFrame = CFrame.new(1084/-2,pos-0.5,1380/2)
	water.Locked = true

	water.TopSurface = 0
	water.BottomSurface = 0
	
	water2 = Instance.new("Part",top)
	water2.Name = "water"
	water2.Anchored = true
	water2.Color = Color3.new(13/255,105/255,172/255)
	water2.Size = Vector3.new(1084,0.2,1380)
	water2.CFrame = CFrame.new(1084/-2,pos+0.4,1380/2)
	water2.Locked = true
	water2.Transparency = 0.5
	
	water2.TopSurface = 0
	water2.BottomSurface = 0

	for i,v in ipairs(co) do
		local hey = coroutine.create(make)
		coroutine.resume(hey,i,v,he,bi,pos,top)
	end
end

newScriptButton.Click:Connect(onNewScriptButtonClicked)